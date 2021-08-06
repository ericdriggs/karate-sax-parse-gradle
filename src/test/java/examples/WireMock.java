package examples;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@SuppressWarnings("unused")
public class WireMock {

    private final static String loggedInResponse =
            "<!DOCTYPE html>\n" +
                    "    <html lang=\"en-US\">\n" +
                    "        <head>\n" +
                    "            <title>Hello world</title>\n" +
                    "            <!-- conforms to spec https://html.spec.whatwg.org/#unquoted -->\n" +
                    "            <script nonce=abc123 type=\"text/javascript\">\n" +
                    "            </script>\n" +
                    "        </head>\n" +
                    "        <body>\n" +
                    "         <h1>Hello</h1>\n" +
                    "        </body>\n" +
                    "    </html>";

    private final WireMockServer wireMockServer = new WireMockServer(options().dynamicHttpsPort());

    public void stop() {
        wireMockServer.stop();
    }

    public int getPort() {
        return wireMockServer.httpsPort();
    }

    public void start() {
        wireMockServer.start();

        wireMockServer
                .stubFor(post(urlEqualTo("/index.html"))
                        .willReturn(aResponse()
                                .withStatus(302)
                                .withHeader("Content-Type", "text/html")
                                .withHeader("Location", "index2.html")
                                .withBody("<!DOCTYPE HTML>\n" +
                                        "<title>Redirecting...</title>\n" +
                                        "<h1>Redirecting...</h1>\n" +
                                        "<p>You should be redirected automatically to target URL: <a href=\"index2.html\">/</a>.  If not click the link.")));

        wireMockServer.stubFor(get(urlEqualTo("/index2.html"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Connection", "keep-alive")
                        .withHeader("Content-Type", "text/html")
                        .withBody(loggedInResponse)));
    }

}
