package com.twsihan.extras.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConverterUtil
{


    public static String bytes2HexString(byte[] b)
    {
        String ret = "";
        System.out.println(b.length);
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public static String toHex(int n)
    {
        StringBuilder sb = new StringBuilder();
        if (n / 16 == 0) {
            return hex(n);
        } else {
            String t = toHex(n / 16);
            int nn = n % 16;
            sb.append(t).append(hex(nn));
        }
        return sb.toString();
    }

    public static String ascii(String str)
    {
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();
        for (int i = 0; i < bs.length; i++)
            sb.append(toHex(bs[i]));
        return sb.toString();
    }

    private static String hex(int n)
    {
        String rt = "";
        switch (n) {
            case 10:
                rt += "A";
                break;
            case 11:
                rt += "B";
                break;
            case 12:
                rt += "C";
                break;
            case 13:
                rt += "D";
                break;
            case 14:
                rt += "E";
                break;
            case 15:
                rt += "F";
                break;
            default:
                rt += n;
        }
        return rt;
    }

    public static String htmlToString(String content)
    {
        if (content == null)
            return "";

        String html = content;
        html = html.replace("'", "&apos;");
        html = html.replaceAll("&", "&amp;");
        html = html.replace("\"", "&quot;"); // "
        html = html.replace("\t", "&nbsp;&nbsp;");// 替换跳格
        html = html.replace(" ", "&nbsp;");// 替换空格
        html = html.replace("<", "&lt;");
        html = html.replaceAll(">", "&gt;");

        return html;
    }

    public static String stringToHtml(String content)
    {
        if (content == null)
            return "";

        String html = content;
        html = html.replace("&apos;", "'");
        html = html.replaceAll("&amp;", "&");
        html = html.replace("&quot;", "\""); // "
        html = html.replace("&nbsp;&nbsp;", "\t");// 替换跳格
        html = html.replace("&nbsp;", " ");// 替换空格
        html = html.replace("&lt;", "<");
        html = html.replaceAll("&gt;", ">");

        return html;
    }

    public static byte[] readInputStream(InputStream inputStream)
    {
        // 定义一个输出流向内存输出数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 定义一个缓冲区
        byte[] buffer = new byte[1024];
        // 读取数据长度
        int len = 0;
        // 当取得完数据后会返回一个-1
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                // 把缓冲区的数据 写到输出流里面
                byteArrayOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        // 得到数据后返回
        return byteArrayOutputStream.toByteArray();
    }

    public static String readHtml(InputStream inputStream, String uncode) throws Exception
    {
        InputStreamReader input = new InputStreamReader(inputStream, uncode);
        BufferedReader bufReader = new BufferedReader(input);
        String line = "";
        StringBuilder contentBuf = new StringBuilder();
        while ((line = bufReader.readLine()) != null) {
            contentBuf.append(line);
        }
        return contentBuf.toString();
    }

    public static String getInputHtmlUTF8(String urlStr)
    {
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setConnectTimeout(5 * 1000);
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() == 200) {
                // 通过输入流获取网络图片
                InputStream inputStream = httpsURLConnection.getInputStream();
                String data = readHtml(inputStream, "UTF-8");
                inputStream.close();
                return data;
            }
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
        return null;
    }

    public static String getInputHtmlGBK(String urlStr)
    {
        URL url = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setConnectTimeout(5 * 1000);
            httpsURLConnection.connect();
            if (httpsURLConnection.getResponseCode() == 200) {
                // 通过输入流获取网络图片
                InputStream inputStream = httpsURLConnection.getInputStream();
                String data = readHtml(inputStream, "GBK");
                inputStream.close();
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
