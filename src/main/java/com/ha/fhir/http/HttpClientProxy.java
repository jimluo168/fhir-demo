package com.ha.fhir.http;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.ClientProtocolException;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.classic.methods.HttpGet;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.HttpClients;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.*;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.io.HttpClientResponseHandler;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.io.entity.EntityUtils;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.message.BasicNameValuePair;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.net.URIBuilder;
import com.ha.fhir.http.annotation.*;
import com.ha.fhir.util.JSONUtil;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: HttpClientInvocationHandler
 * @description: TODO 类描述
 * @author: Jim Luo
 * @date: 2022/6/1
 **/
public class HttpClientProxy<T> implements InvocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientProxy.class);
    private static final Pattern REGEX_URL = Pattern.compile("(\\$\\{)([a-zA-Z\\d.]+)((:)*)(})(([a-zA-Z\\d/]+)|$)");

    private Class<T> interfaceType;

    public HttpClientProxy(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        logger.info("invoke proxy:{} method:{} args:{}", proxy, method, args);
        Annotation[] clsAnnos = method.getDeclaringClass().getAnnotations();
        if (!Arrays.stream(clsAnnos).filter(annotation -> annotation.annotationType().equals(HttpClient.class)).findAny().isPresent()) {
            throw new RuntimeException("class must be add @HttpClient");
        }
        Annotation[] methodAnnotations = method.getAnnotations();
        if (methodAnnotations == null || methodAnnotations.length == 0) {
            throw new RuntimeException("HttpClient Respository method must be @GET,@POST,@PUT,@DELETE");
        }
        HttpClient httpClient = (HttpClient) Arrays.stream(clsAnnos).filter(annotation -> annotation.annotationType().equals(HttpClient.class)).findFirst().get();
        ResponseBody responseBodyAnno = (ResponseBody) Arrays.stream(methodAnnotations).filter(annotation -> annotation.annotationType().equals(ResponseBody.class)).findFirst().get();
        Annotation methodAnno =
                Arrays.stream(methodAnnotations).filter(
                        annotation -> annotation.annotationType().equals(Get.class)
                                || annotation.annotationType().equals(Post.class)
                                || annotation.annotationType().equals(Put.class)
                                || annotation.annotationType().equals(Delete.class)
                ).findFirst().get();
        String url = httpClient.url();
        Matcher urlMatcher = REGEX_URL.matcher(url);

        if (urlMatcher.matches()) {
            String envKey = urlMatcher.group(2);
            String value = System.getProperty(envKey);
            url = url.replaceAll("\\$\\{" + envKey + "}", value);
        }
        // 暂时先完善Get方法.
        if (methodAnno.annotationType().equals(Get.class)) {
            Get get = (Get) methodAnno;
            String path = get.path();

            Annotation[][] parameterAnnos = method.getParameterAnnotations();
            List<NameValuePair> nvpList = new ArrayList<>();
            for (int i = 0; i < parameterAnnos.length; i++) {
                Annotation[] parameterAnno = parameterAnnos[i];
                if (parameterAnno.length == 0) {
                    continue;
                }
                if (parameterAnno[0].annotationType().equals(PathParam.class)) {
                    PathParam pathParam = (PathParam) parameterAnno[0];
                    path = path.replaceAll("\\{" + pathParam.value() + "}", object2Str(args[i]));
                } else {
                    Param param = (Param) parameterAnno[0];
                    Object value = args[i];
                    NameValuePair nvp = new BasicNameValuePair(param.name(), object2Str(value));
                    nvpList.add(nvp);
                }
            }
            url = url + path;
            URI uri = new URIBuilder(new URI(url)).addParameters(nvpList).build();
            HttpGet request = new HttpGet(uri);

            try (CloseableHttpClient client = HttpClients.createDefault()) {
                Object returnObj = null;
                //TODO 此处暂时FHIR标准格式数据处理，后续有其他格式再完善.
                Class<IBaseResource> returnType = (Class<IBaseResource>) method.getReturnType();

                String resposeBody = client.execute(request, fhirStandardFormat(uri));
                switch (responseBodyAnno.format()) {
                    case FHIR_JSON:
                        FhirContext myFhirContext = FhirContext.forR4();
                        IParser parser = myFhirContext.newJsonParser();
                        returnObj = parser.parseResource(returnType, resposeBody);
                        break;
                    case FHIR_XML:
                        myFhirContext = FhirContext.forR4();
                        parser = myFhirContext.newXmlParser();
                        returnObj = parser.parseResource(returnType, resposeBody);
                        break;
                    case JSON:
                        returnObj = JSONUtil.parseObject(resposeBody, returnType);
                        break;
                    case XML:
                        throw new UnSupportedDataFormatException(responseBodyAnno.format() + " format not implemented");
                    default:
                        throw new UnSupportedDataFormatException(responseBodyAnno.format() + " format unsupported");

                }
                return returnObj;

            }
        } else if (methodAnno.annotationType().equals(Post.class)) {
            Post post = (Post) methodAnno;
            url = url + post.path();
            throw new RuntimeException(methodAnno.annotationType() + "not implemented");
        } else if (methodAnno.annotationType().equals(Put.class)) {
            Put put = (Put) methodAnno;
            url = url + put.path();
            throw new RuntimeException(methodAnno.annotationType() + "not implemented");
        } else if (methodAnno.annotationType().equals(Delete.class)) {
            Delete delete = (Delete) methodAnno;
            url = url + delete.path();
            throw new RuntimeException(methodAnno.annotationType() + "not implemented");
        } else {
            throw new RuntimeException(methodAnno.annotationType() + "not implemented");
        }
    }

    private String object2Str(Object obj) {
        return String.valueOf(obj);
    }

    public HttpClientResponseHandler<String> fhirStandardFormat(URI requestUri) {
        return response -> {
            final int status = response.getCode();
            if (status >= HttpStatus.SC_SUCCESS && status < HttpStatus.SC_REDIRECTION) {
                final HttpEntity entity = response.getEntity();
                try {
                    return entity != null ? EntityUtils.toString(entity) : null;
                } catch (final ParseException ex) {
                    logger.error("request url:{} parse exception: {}", requestUri, ex);
                    throw new ClientProtocolException(ex);
                }
            } else {
                logger.error("request url:{} Unexpected response status: {}", requestUri, status);
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }
}
