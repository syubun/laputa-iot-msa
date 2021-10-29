package com.laputa.iot.monitor.support;

import com.alibaba.druid.support.http.ResourceServlet;
import com.laputa.iot.common.core.util.SpringContextHolder;
import com.laputa.iot.monitor.service.impl.MonitorStatService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;

/**
 * @author linchtech
 * @date 2020-09-16 11:10
 **/
@Slf4j
public class MonitorViewServlet extends ResourceServlet {

	private MonitorStatService monitorStatService;

	public MonitorViewServlet() {
		super("druid");
	}

	@Override
	public void init() throws ServletException {
		log.info("init MonitorViewServlet");
		super.init();
		monitorStatService = SpringContextHolder.getBean(MonitorStatService.class);
	}

	@Override
	protected String process(String url) {
		return monitorStatService.service(url);
	}

}
