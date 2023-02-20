package com.librarycommon.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.librarycommon.exception.LibraryCommonException;
import com.librarycommon.vo.ResponseVo;
import com.librarycommon.vo.SubResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class LibraryCommonUtility {

    public static void createSuccessResponse(ResponseVo responseVo) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<SubResponseVo> subResponses = new ArrayList<>();
            SubResponseVo subResponseVo = new SubResponseVo(ApiMsgConstants.SUCCESS_CODE, ApiMsgConstants.SUCCESS_MSG);
            subResponses.add(subResponseVo);
            ArrayNode arrayNode = mapper.valueToTree(subResponses);
            String jsonInString = mapper.writeValueAsString(responseVo.getData());
            JsonNode rootNode = mapper.readTree(jsonInString);
            ((ObjectNode) rootNode).put(ApiMsgConstants.RESPCODE, ApiMsgConstants.SUCCESS_CODE);
            ((ObjectNode) rootNode).set(ApiMsgConstants.RESPMSG, arrayNode);
            responseVo.setData(rootNode);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("Error in createSuccessResponse in LibraryCommonUtility class ::", e);
            }
        }
    }

    public static void createErrorResponse(LibraryCommonException libraryCommonException){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<SubResponseVo> subResponses = new ArrayList<>();
            SubResponseVo subResponse = new SubResponseVo(libraryCommonException.getErrorCode(), libraryCommonException.getErrorMessage());
            subResponses.add(subResponse);
            ArrayNode array = objectMapper.valueToTree(subResponses);
            String jsonInString = objectMapper.writeValueAsString(libraryCommonException.getResponseVo());
            JsonNode rootNode = objectMapper.readTree(jsonInString);
            ((ObjectNode)rootNode).put(ApiMsgConstants.RESPCODE, libraryCommonException.getErrorCode());
            ((ObjectNode)rootNode).set(ApiMsgConstants.RESPMSG, array);
            libraryCommonException.getResponseVo().setData(rootNode);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("Error in createErrorResponse in LibraryCommonUtility class ::", e);
            }
        }
    }

    public static Long getTotalPages (int size, Long totalElements) {
        Long totalPages = 0L;
        if (size > 0 && totalElements > 0) {
            Long result = totalElements % size;
            Long pages = totalElements / size;
            if (result >= 1) {
                totalPages = pages + 1;
            }
        }
        return totalPages;
    }


}
