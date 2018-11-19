package com.questionservice;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Application;


@Path("/get")
public class QuestionService extends Application{
  
  @GET
  @Path("questions/{testId}/{taskId}")
  @Produces("text/plain")
  public String getQuestions(@PathParam("testId") String testId, @PathParam("taskId") String taskId) {
   
    int testId2 = Integer.parseInt(testId);
    int taskId2 = Integer.parseInt(taskId);
    String html = "";
    
    try {
      Context context = new InitialContext();
      DataSource ds = (DataSource) context.lookup("java:/MySqlDS");
      Connection conn = ds.getConnection();
      
      PreparedStatement pStmt = conn.prepareStatement("select html, preEvent "
          + " from retrieval_quizes where testId=? and taskIndex=?");
      pStmt.setInt(1,testId2);
      pStmt.setInt(2, taskId2);
      ResultSet res = pStmt.executeQuery();
      
      if(!res.last()) {
        html = "Either no task " + taskId + " or no test " + testId + " found!";
      } else {
        res.beforeFirst();
        while(res.next()) {
          if(res.isLast()) {
            html += res.getString("html");
          }
        }
      }
      
      conn.close();

    }  catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NamingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return html;
  }
  
  @GET
  @Path("answers/{testId}/{taskId}")
  @Produces("text/plain")
  public String getAnswers(@PathParam("testId") String testId, @PathParam("taskId") String taskId) {
   
    int testId2 = Integer.parseInt(testId);
    int taskId2 = Integer.parseInt(taskId);
    String answers = "";
    
    try {
      Context context = new InitialContext();
      DataSource ds = (DataSource) context.lookup("java:/MySqlDS");
      Connection conn = ds.getConnection();
      
      PreparedStatement pStmt = conn.prepareStatement("select answers"
          + " from retrieval_quizes where testId=? and taskIndex=?");
      pStmt.setInt(1,testId2);
      pStmt.setInt(2, taskId2);
      ResultSet res = pStmt.executeQuery();
      
      if(!res.last()) {
        answers = "Either no task " + taskId + " or no test " + testId + " found!";
      } else {
        res.beforeFirst();
        while(res.next()) {
          if(res.isLast()) {
            answers = res.getString("answers");
          } 
          
        }
      }
      
      
      conn.close();

    }  catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NamingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return answers;
  }
  
  @GET
  @Path("preEvent/{testId}/{taskId}")
  @Produces("text/plain")
  public String getPreEvent(@PathParam("testId") String testId, @PathParam("taskId") String taskId) {
   
    int testId2 = Integer.parseInt(testId);
    int taskId2 = Integer.parseInt(taskId);
    String answers = "";
    
    try {
      Context context = new InitialContext();
      DataSource ds = (DataSource) context.lookup("java:/MySqlDS");
      Connection conn = ds.getConnection();
      
      PreparedStatement pStmt = conn.prepareStatement("select preEvent"
          + " from retrieval_quizes where testId=? and taskIndex=?");
      pStmt.setInt(1,testId2);
      pStmt.setInt(2, taskId2);
      ResultSet res = pStmt.executeQuery();
      
      if(!res.last()) {
        answers = "Either no task " + taskId + " or no test " + testId + " found!";
      } else {
        res.beforeFirst();
        while(res.next()) {
          if(res.isLast()) {
            answers = res.getString("preEvent");
          } 
          
        }
      }
      
      
      conn.close();

    }  catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NamingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return answers;
  }

}
