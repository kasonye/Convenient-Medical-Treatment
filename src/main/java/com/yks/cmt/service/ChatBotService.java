package com.yks.cmt.service;

import com.huaweicloud.sdk.cbs.v1.CbsClient;
import com.huaweicloud.sdk.cbs.v1.model.ExecuteQaChatRequest;
import com.huaweicloud.sdk.cbs.v1.model.ExecuteQaChatResponse;
import com.huaweicloud.sdk.cbs.v1.model.PostRequestsReq;
import com.huaweicloud.sdk.cbs.v1.region.CbsRegion;
import com.huaweicloud.sdk.core.auth.BasicCredentials;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class ChatBotService {
    @Value("${huaweicloud.chatbot.projectId}")
    private String projectId;

    @Value("${huaweicloud.chatbot.endpoint}")
    private String endpoint;

    @Value("${huaweicloud.chatbot.robotId}")
    private String qaBotID;

    @Value("{huaweicloud.chatbot.region}")
    private String region;

    @Value("${huaweicloud.chatbot.ak}")
    private String ak;

    @Value("${huaweicloud.chatbot.sk}")
    private String sk;

    public ResponseEntity<?> askQuestion(String question){
        BasicCredentials auth = new BasicCredentials()
                .withAk(ak)
                .withSk(sk)
                .withProjectId(projectId);
        CbsClient client = CbsClient.newBuilder()
                .withCredential(auth)
                .withEndpoint(endpoint)
                .build();

        ExecuteQaChatRequest request = new ExecuteQaChatRequest();
        request.withQabotId(qaBotID);
        PostRequestsReq body = new PostRequestsReq();
        body.withQuestion(question);
        request.withBody(body);
        try {

            ExecuteQaChatResponse response = client.executeQaChat(request);
            System.out.println(response.toString());
            if (response.getQabotAnswers()!=null){
                if(response.getQabotAnswers().getAnswers().size()!=0){
                    String answer = response.getQabotAnswers().getAnswers().get(0).getAnswer();
                    String substring = answer.substring(3, answer.length() - 4);
                    return ResponseEntity.ok(substring);
                }else{
                    String answer = response.getQabotAnswers().getRecommendAnswers().get(0).getTopScoreQuestion();
                    return ResponseEntity.ok("你想查询的问题是："+answer);
                }
            }else {
                return ResponseEntity.ok("对不起，我没有找到相关的问题。您可能想找的问题是: e.g:糖尿病，高血压");
            }

        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
