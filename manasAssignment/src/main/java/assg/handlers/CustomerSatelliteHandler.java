package assg.handlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import assg.Main;
import assg.common.AppUtils;
import assg.model.CustomerSatelliteModel;
import assg.service.impl.CustomerSatelliteImpl;

public class CustomerSatelliteHandler extends AbstractHandler implements GetStringFromInputStream{

	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		baseRequest.setHandled(true);

		assg.service.CustomerSatellite customerSatelliteService = new CustomerSatelliteImpl();

		HashMap<String, String> reqParam = (HashMap<String, String>) AppUtils.getReqParam(request);

		String method = request.getMethod();
		String body = getStringFromInputStream(request.getInputStream());

		if (baseRequest.getRequestURI().contains("dump-data"))
			try {
				boolean res = customerSatelliteService.dumpData();
				if (res) {
					Main.infoLog.info("Data Dumped Successfully");
					response.getWriter().println("200+ Success");
				}
			} catch (SQLException e) {
				Main.infoLog.error("Error while dumping the data"+ e);
				response.getWriter().println("Fail");
			}

		else {
			if ("GET".equalsIgnoreCase(method))
				if (reqParam.containsKey("list"))
					try {
						List<CustomerSatelliteModel> list = customerSatelliteService.readCustomerSatellite(reqParam.get("list"));
						Main.infoLog.info("Fetched List : " + list);
						if (!list.isEmpty()) {
							response.getWriter().println("200+ Success");
						}
					} catch (SQLException e) {
						Main.infoLog.error("Error while fetching the data"+ e);
						response.getWriter().println("Fail");
					}
				else if (reqParam.containsKey("delete"))
					try {
						boolean res = customerSatelliteService.deleteCustomerSatellite(reqParam.get("delete"));
						if (res) {
							Main.infoLog.info("Data Deleted Successfully");
							response.getWriter().println("200+ Success");
						}
					} catch (SQLException e) {
						Main.infoLog.error("Error while deleting the data"+ e);
						response.getWriter().println("Fail");
					}
				else
					response.getWriter().println("Invalid Request+ Fail");

			else if ("POST".equalsIgnoreCase(method)) {
				List<CustomerSatelliteModel> customerSatellite = CustomerSatelliteModel.fromJson(body);
				if (reqParam.containsKey("add"))
					if ("auto".equalsIgnoreCase(reqParam.get("add")))
						try {
							boolean res = customerSatelliteService.insertCustomerSatellite(customerSatellite);
							if (res) {
								Main.infoLog.info("Data Inserted Successfully");
								response.getWriter().println("200+ Success");
							}
						} catch (SQLException e) {
							Main.infoLog.error("Error while inserting the data"+ e+ "\t for : "+ customerSatellite.toString());
							response.getWriter().println("Fail");
						}
					else
						try {
							boolean res = customerSatelliteService.updateCustomerSatellite(customerSatellite.get(0));
							if (res) {
								Main.infoLog.info("Data Updated Successfully");
								response.getWriter().println("200+ Success");
							}
						} catch (SQLException e) {
							Main.infoLog.error("Error while updating the data"+ e+ "\t for : "+ customerSatellite.toString());
							response.getWriter().println("Fail");
						}

				else
					response.getWriter().println("Invalid Request+ Fail");

			}

		}
	}

}
