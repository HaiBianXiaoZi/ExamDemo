package com.migu.schedule;


import com.migu.schedule.constants.ReturnCodeKeys;
import com.migu.schedule.info.TaskInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
*类名和方法不能修改
 */
public class Schedule {

	//挂起队列
	ArrayList<TaskInfo> hangQueue = new ArrayList<TaskInfo>();
	
	//任务队列
	HashMap<Integer,TaskInfo> taskQueue = new 	HashMap<Integer,TaskInfo>();
	
	//节点任务分配
	HashMap<Integer,ArrayList<TaskInfo>>  taskQueueMap = new HashMap<Integer,ArrayList<TaskInfo>>();
	
    public int init() {
        // TODO 方法未实现
    	hangQueue = new ArrayList<TaskInfo>();
    	taskQueueMap = new HashMap<Integer,ArrayList<TaskInfo>>();
        return ReturnCodeKeys.E001;
    }


    public int registerNode(int nodeId) {
        // TODO 方法未实现
    	if(nodeId <= 0) return ReturnCodeKeys.E004;
    	
    	if(taskQueueMap.containsKey(nodeId))
    	{
    		return ReturnCodeKeys.E005;
    	}
    	taskQueueMap.put(nodeId, new ArrayList<TaskInfo>());
        return ReturnCodeKeys.E003;
    }

    public int unregisterNode(int nodeId) {
        // TODO 方法未实现
    	if(nodeId <= 0) return ReturnCodeKeys.E004;
    	
    	if(!taskQueueMap.containsKey(nodeId))
    	{
    		return ReturnCodeKeys.E007;
    	}
    	if(!taskQueueMap.get(nodeId).isEmpty())
    	{
    		for(TaskInfo task : taskQueueMap.get(nodeId))
    		{
    			task.setNodeId(-1);
    			hangQueue.add(task);
    		}
    	}
    	taskQueueMap.remove(nodeId);
        return ReturnCodeKeys.E006;
    }


    public int addTask(int taskId, int consumption) {
        // TODO 方法未实现
    	if(taskId <= 0) return ReturnCodeKeys.E009;
    	
    	if(taskQueue.containsKey(taskId)) return ReturnCodeKeys.E010;
    	
    	TaskInfo task = new TaskInfo();
    	task.setTaskId(taskId);
    	task.setConsumption(consumption);
    	task.setNodeId(-1);
    	hangQueue.add(task );
    	taskQueue.put(taskId, task);
    	
        return ReturnCodeKeys.E008;
    }


    public int deleteTask(int taskId) {
        // TODO 方法未实现
    	if(taskId <= 0) return ReturnCodeKeys.E009;
    	
    	if(!taskQueue.containsKey(taskId)) return ReturnCodeKeys.E012;
    	
    	TaskInfo task = taskQueue.get(taskId);
    	if(task.getNodeId() == -1)
    	{
    		hangQueue.remove(task);
    	}
    	else
    	{
    		taskQueueMap.get(task.getNodeId()).remove(task);
    	}
    	taskQueue.remove(taskId);
        return ReturnCodeKeys.E011;
    }


    public int scheduleTask(int threshold) {
        // TODO 方法未实现
    	if(threshold <=0) return ReturnCodeKeys.E002;
    	
    	int nodeCount = taskQueueMap.size();
    	ArrayList<Integer> consumptions = new ArrayList<Integer>();
    	
    	int sum = 0;
    	double ave = 0;//平均值
    	for(TaskInfo task : taskQueue.values())
    	{
    		consumptions.add(task.getConsumption());
    		sum += task.getConsumption();
    	}
    	Collections.sort(consumptions);
    	ave = sum*1.0/consumptions.size();
    	
    
        return ReturnCodeKeys.E000;
    }
    
    public static void main(String[] args) {
		Schedule schedule = new Schedule();
		int actual = schedule.init();
		schedule.registerNode(7);
        schedule.registerNode(1);
        schedule.registerNode(6);
        
        schedule.addTask(1, 2);
        schedule.addTask(2, 14);
        schedule.addTask(3, 4);
        schedule.addTask(4, 16);
        schedule.addTask(5, 6);
        schedule.addTask(6, 5);
        schedule.addTask(7, 3);
        
        actual = schedule.scheduleTask(10);
	}
    double[] findMin(double[] db )
    {
    	double min = Double.MAX_VALUE;
    	int index = 0;
    	for(int i=0;i<db.length;i++)
    	{
    		double db1 =db[i];
    		if(min >db1)
    		{
    			min = db1;
    			index = i;
    		}
    	}
    	return new double[]{min,index};
    }


    public int queryTaskStatus(List<TaskInfo> tasks) {
        // TODO 方法未实现
    	if(tasks == null) return ReturnCodeKeys.E016;
    	tasks.clear();
    	tasks.addAll(taskQueue.values());
    	Collections.sort(tasks);
        return ReturnCodeKeys.E015;
    }

}
