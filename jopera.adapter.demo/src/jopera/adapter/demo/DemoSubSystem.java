package jopera.adapter.demo;

import org.jopera.kernel.IJob;
import org.jopera.kernel.State;
import org.jopera.kernel.dispatcher.SubSystemAbstract;

/**
 * The DemoSubSystem shows how to write a hallo world JOpera adapter
 * 
 * The set of input/output system parameters are declared in the demo.oml file
 * 
 * @author Cesare Pautasso
 *
 */
public class DemoSubSystem extends SubSystemAbstract {

	public void Execute(IJob job) {

		try {
			//retrieve and validate the input system parameters
			String input      = validateInput(job, "INPUT");
			String input_url  = validateInput(job, "INPUT_URL");
			String input_text = validateInput(job, "INPUT_TEXT");
			String input_xml  = validateInput(job, "INPUT_XML");
							
			//do something with the input
			System.out.println("DEMO Adapter Test");
			System.out.println(input);
			System.out.println(input_text);
			System.out.println(input_url);
			System.out.println(input_xml);

			//store the result of the job execution
			//this will just print it out 
			job.getOutput().put("Output", input);
			job.getSystemOutput().put("OUTPUT", input);
			
			//tell JOpera that the job has completed successfully
			job.setState(State.FINISHED);
		}
		catch(Throwable e) {
			//tell JOpera that the job has failed its execution
			failJob(job, "DEMO Job Failed: "+e.getMessage());
		}
		
	}

	private String validateInput(IJob job, String parameter) {
		
		String value = (String) job.getActiveCommand().get(parameter);
		
		if (value == null) {
			throw new IllegalArgumentException("Input Parameter: "+parameter+" is null");
		}
		
		if (value.length() == 0) {
			throw new IllegalArgumentException("Input Parameter: "+parameter+" is empty");
		}
		
		return value;
	}	
	
}
