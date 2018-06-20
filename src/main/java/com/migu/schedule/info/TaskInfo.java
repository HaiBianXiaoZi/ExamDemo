package com.migu.schedule.info;

/**
 * 任务状态信息类，请勿修改。
 *
 * @author
 * @version
 */
public class TaskInfo implements Comparable<TaskInfo>
{
    private int taskId;
    private int nodeId;
    private int consumption;
    public int getNodeId()
    {
        return nodeId;
    }
    public int getTaskId(){  return taskId; }
    
    public int getConsumption(){  return consumption; }
    public void setNodeId(int nodeId)
    {
        this.nodeId = nodeId;
    }
    public void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }
    public void setConsumption(int consumption)
    {
        this.consumption = consumption;
    }
    
    @Override
    public String toString()
    {
        return "TaskInfo [taskId=" + taskId + ", nodeId=" + nodeId + ", consumption=" + consumption + "]";
    }
	public int compareTo(TaskInfo o) {
		
		if(taskId >o.taskId) return 1;
		if(taskId < o.taskId) return -1;
		return 0;
	}
}
