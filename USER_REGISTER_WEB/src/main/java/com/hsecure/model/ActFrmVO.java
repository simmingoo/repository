package com.hsecure.model;

import java.util.List;
import java.util.Map;

public class ActFrmVO
{
  private String processDefinitionId;
  private List<Map> variables;

  public String getProcessDefinitionId()
  {
    return this.processDefinitionId;
  }

  public void setProcessDefinitionId(String processDefinitionId)
  {
    this.processDefinitionId = processDefinitionId;
  }

  public List<Map> getVariables()
  {
    return this.variables;
  }

  public void setVariables(List<Map> variables)
  {
    this.variables = variables;
  }
}