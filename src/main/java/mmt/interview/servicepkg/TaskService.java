package mmt.interview.servicepkg;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mmt.interview.beans.Task;
import mmt.interview.utils.HttpService;

@Component
public class TaskService {
	
	@Autowired
	HttpService httpService;

	public long executeTasks(List<Task> tasks)
	{
		long totalTime = 0;
		for(Task task : tasks)
		{
			long timePerTask = 0;
			if(task.isParallel())
			{
				Date startTime = new Date();
				ExecutorService executorService = Executors.newFixedThreadPool(Integer.valueOf(task.getCount()));
				Future future = null;
				for (int i = 1; i < Integer.valueOf(task.getCount()); i++) {
					future = executorService.submit(new Runnable() {
					    public void run() {
					    	httpService.callAsyncService(task.getUrl());
					    }
					});
				}
				executorService.shutdown();
				try {
					while(future.get() != null)
					{
						
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Date endTime = new Date();
				timePerTask = endTime.getTime()-startTime.getTime();
			}
			else if(!task.isParallel())
			{
				Date startTime = new Date();
				for(int i=1; i<=Integer.valueOf(task.getCount()); i++)
				{
					httpService.callSequentialService(task.getUrl());
				}
				Date endTime = new Date();
				timePerTask = endTime.getTime()-startTime.getTime();
			}
			totalTime=totalTime+timePerTask;
		}
		return totalTime;
	}
}
