/**
* This file is part of dev.utils.
*
* dev.utils is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* You should have received a copy of the GNU General Public License
* along with dev.utils.  If not, see <http://www.gnu.org/licenses/>.
*
* File      : URLEncoder.java
* Created   : November 23, 2006, 2:38 PM
*/

package dev.utils.net;

import dev.utils.log.Logger;
import java.net.URL;
import java.util.StringTokenizer;

/**
 *  encodes the whole URL given as parameter, <br>
 *  with its static encode method 
 * 
 *  @version 1.0
 *  @author Erol Hira
 */
public class URLEncoder {

    /** Creates a new instance of URLEncoder */
    public URLEncoder() {
    }

    public static String encode(String netAdd) {
        
        StringBuffer stringBuffer = new StringBuffer();

        try {
            URL url = new URL(netAdd);
            stringBuffer.append(url.getProtocol());
            stringBuffer.append("://");

            if (url.getUserInfo() != null) {
                stringBuffer.append(java.net.URLEncoder.encode(url.getUserInfo(),
                        "UTF-8") + "@");
            }

            if (url.getHost() != null) {
                stringBuffer.append(java.net.URLEncoder.encode(url.getHost(),
                        "UTF-8"));
            }

            if (url.getPort() != -1) {
                stringBuffer.append(":" + url.getPort());
            }

            if (url.getPath() != null) {
                StringTokenizer sp = new StringTokenizer(url.getPath(), "/", true);
                while (sp.hasMoreTokens()) {
                    String token = sp.nextToken();
                    if (token.equalsIgnoreCase("/")) {
                        stringBuffer.append(token);
                    } else {
                        stringBuffer.append(java.net.URLEncoder.encode(token,
                                "UTF-8"));
                    }
                }
            }

            String query = url.getQuery();
            if (query != null) {
                stringBuffer.append("?");
                StringTokenizer st = new StringTokenizer(query, "&=", true);

                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    boolean isAnd = token.equalsIgnoreCase("&");
                    boolean isEqu = token.equalsIgnoreCase("=");

                    if (isAnd || isEqu) {
                        stringBuffer.append(token);
                    } else {
                        stringBuffer.append(java.net.URLEncoder.encode(token,
                                "UTF-8"));
                    }
                }

                if (url.getRef() != null) {
                    stringBuffer.append("#" + url.getRef());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger().error("Desteklenmeyen Kodlama Metodu UTF-8", ex, URLEncoder.class);
        }

        return stringBuffer.toString();
    }
}
