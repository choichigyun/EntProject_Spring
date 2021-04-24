<%@page import="com.hanul.ent.dto.EntDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%		
	Gson gson = new Gson();
	String json = gson.toJson((ArrayList<EntDTO>)request.getAttribute("list"));
	
	out.println(json);
%>	