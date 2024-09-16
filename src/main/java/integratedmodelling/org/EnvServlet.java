package integratedmodelling.org;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnvServlet
 */
public class EnvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ENV_PREFIX = "KHUB_";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnvServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/javascript");
		
		Map<String, String> environmentVariables = System.getenv();
		
		Map<String, String> kHubEnvironmentVariables = environmentVariables.entrySet().stream().filter((v) -> 
			v.getKey().startsWith(ENV_PREFIX)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
				
		String jsonFormatData = kHubEnvironmentVariables.entrySet().stream()
		.map(e -> String.format("\t\"%s\":\"%s\"", e.getKey(), e.getValue()))
		.collect(Collectors.joining(",\n", "{\n", "\n}"));		
		
		response.getWriter().append("var __ENV__= " + jsonFormatData);	
				
	}


}
