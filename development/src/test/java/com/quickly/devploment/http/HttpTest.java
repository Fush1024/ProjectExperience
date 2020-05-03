package com.quickly.devploment.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName HttpTest
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/12 14:03
 * @Version V-1.0
 **/
@Slf4j
public class HttpTest {

	@Test
	public void testHttpHost() {
		HttpHost proxy = new HttpHost("127.0.0.1", 9876, "HTTP");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet request = new HttpGet("url");
		try {
			CloseableHttpResponse response = httpclient.execute(proxy, request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1(){
		Set<Integer> integers = new HashSet<>();
		integers.add(1);

		System.out.println(integers.contains(1));
	}

	@Test
	public void testIpAddress(){
		ArrayList inetAddressList = new ArrayList();

		try {
			Enumeration enumeration = NetworkInterface.getNetworkInterfaces();

			while(enumeration.hasMoreElements()) {
				NetworkInterface networkInterface = (NetworkInterface)enumeration.nextElement();
				Enumeration addrs = networkInterface.getInetAddresses();

				while(addrs.hasMoreElements()) {
					inetAddressList.add(((InetAddress)addrs.nextElement()).getHostAddress());
				}
			}

			InetAddress addr = InetAddress.getLocalHost();
			System.out.println(addr.getHostAddress());
		} catch (Exception var4) {
			throw new RuntimeException("get local inet address fail", var4);
		}
		log.info("inetAddressList --> {}", inetAddressList);



	}
}
