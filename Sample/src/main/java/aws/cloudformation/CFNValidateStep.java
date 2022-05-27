package aws.cloudformation;



import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClient;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudformation.model.AmazonCloudFormationException;
import com.amazonaws.services.cloudformation.model.ValidateTemplateRequest;
import com.amazonaws.services.cloudformation.model.ValidateTemplateResult;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.yaml.snakeyaml.Yaml;

import java.util.stream.Collectors;

public class CFNValidateStep  {
    public static void main(String[] args) throws FileNotFoundException {
        AmazonCloudFormationClient client = new AmazonCloudFormationClient();
        try {

            Yaml yaml = new Yaml();
            InputStream inputStream = new FileInputStream("C:\\Users\\KIIT\\Desktop\\aws\\quickstart-amazon-eks-main\\templates\\amazon-eks-entrypoint-new-vpc.template.yaml");
            String template = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            ValidateTemplateRequest request = new ValidateTemplateRequest();
            if (template != null) {
                request.withTemplateBody(template);
            }else {
               request.withTemplateURL(request.getTemplateURL());
            }
            ValidateTemplateResult result = client.validateTemplate(request);

            System.out.println(result);
        } catch (AmazonCloudFormationException | IOException e) {
            System.out.println("Error in the File!!");
        }
    }
}


