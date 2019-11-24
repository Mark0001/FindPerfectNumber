package mark.findPerfectNumber;

import java.util.List;

import org.apache.log4j.Logger;

import work.Work;

public class Worker extends Work implements Runnable {
    final static Logger logger = Logger.getLogger(Worker.class);


    public Worker(final int start, final int end) {
    	super(start, end);
    }

    @Override
    public void run() {
    	List<Integer> ans = super.getPerfactNumber();
    	if(!ans.isEmpty()) {
    		for(Integer i : ans) {
    			MainUseTreadPool.addRank(i);
    		}
    	}
    }

}
