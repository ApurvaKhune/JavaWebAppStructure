package com.qount.wallremainders.controller;

/**
 * 
 * @author apurva
 * @version 1.0
 * Feb 5th 2018
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qount.wallremainders.common.PropertyManager;
import com.qount.wallremainders.utils.CommonUtils;
import com.qount.wallremainders.utils.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Project Metadata Controller")
@Path("/")
public class ProjectVersionController {
	/**
	 * this method is used to get the Current version of Project
	 */
	@GET
	@Path("version")
	@ApiOperation(value = "Returns Project Current version ", notes = "Used to to get Project Current version", responseContainer = "java.lang.String")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectCurrentVersion() {
		return CommonUtils.constructResponse(PropertyManager.getProperty(Constants.PROJECT_CURRENT_VERSION, null),Constants.SUCCESS_RESPONSE_CODE);

	}

}