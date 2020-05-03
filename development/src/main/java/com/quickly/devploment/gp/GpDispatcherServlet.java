package com.quickly.devploment.gp;

import com.quickly.devploment.gp.context.GPApplicationContext;
import com.quickly.devploment.gp.webmvc.GpHandlerAdapter;
import com.quickly.devploment.gp.webmvc.GpHandlerMapping;
import com.quickly.devploment.gp.webmvc.GpViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName gpDispatcherServlet
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/14 10:31
 * @Version V-1.0
 **/
public class GpDispatcherServlet extends HttpServlet {

	private final String LOCATION = "contextConfigLocation";

	private List<GpHandlerMapping> handlerMappings = new ArrayList<GpHandlerMapping>();

	private Map<GpHandlerMapping, GpHandlerAdapter> handlerAdapters = new HashMap<>();

	private List<GpViewResolver> viewResolvers = new ArrayList<GpViewResolver>();

	private GPApplicationContext applicationContext;



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
        applicationContext = new GPApplicationContext(config.getInitParameter(LOCATION));
        initStrategies(applicationContext);
	}

	protected void initStrategies(GPApplicationContext applicationContext){
		// handlerMapping 等东西
	}
}
