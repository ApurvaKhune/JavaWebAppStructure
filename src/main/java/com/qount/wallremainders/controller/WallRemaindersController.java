package com.qount.wallremainders.controller;

/**
 * 
 * @author apurva
 * @version 1.0
 * Feb 5th 2018
 */
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qount.wallremainders.controllerImpl.WallRemaindersControllerImpl;
import com.qount.wallremainders.model.WallRemainders;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Wall_remainders Controller")
@Path("/users/{userId}/companies/{companyId}/wallremainders")
public class WallRemaindersController {

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Returns Wall_remainders", notes = "Used to get of Wall_remainders by id", responseContainer = "java.lang.String")
	@Produces(MediaType.APPLICATION_JSON)
	public WallRemainders getWallRemainder(@NotNull @PathParam("userId") String userId,
			@NotNull @PathParam("companyId") String companyId, @NotNull @PathParam("id") String id) {
		return WallRemaindersControllerImpl.getWallRemainder(userId, companyId, id);
	}

	@GET
	@ApiOperation(value = "Returns list of Wall_remainderss", notes = "Used to get list of Wall_remainderss", responseContainer = "java.lang.String")
	@Produces(MediaType.APPLICATION_JSON)
	public List<WallRemainders> getAllWallRemainders(@NotNull @PathParam("userId") String userId,
			@NotNull @PathParam("companyId") String companyId) {
		return WallRemaindersControllerImpl.getAllWallRemainders(userId, companyId);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Wall_remainders", notes = "Deletes Wall_remainders by id", responseContainer = "java.lang.String")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteWall_remainders(@NotNull @PathParam("userId") String userId,
			@NotNull @PathParam("companyId") String companyId, @NotNull @PathParam("id") String id) {
		return WallRemaindersControllerImpl.deleteWall_remainders(userId, companyId, id);
	}

	@POST
	@ApiOperation(value = "Create Wall_remainders", notes = "Used to create Wall_remainders", responseContainer = "java.lang.String")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createWallRemainder(@NotNull @PathParam("userId") String userId,
			@NotNull @PathParam("companyId") String companyId, WallRemainders wall_remainders) {
		return WallRemaindersControllerImpl.createWallRemainder(userId, companyId, wall_remainders);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Wall_remainders", notes = "Update Wall_remainders by id", responseContainer = "java.lang.String")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateWall_remainders(@NotNull @PathParam("userId") String userId,
			@NotNull @PathParam("companyId") String companyId, @NotNull @PathParam("id") String id,
			WallRemainders Wall_remainders) {
		return WallRemaindersControllerImpl.updateWall_remainders(userId, companyId, id, Wall_remainders);
	}

}