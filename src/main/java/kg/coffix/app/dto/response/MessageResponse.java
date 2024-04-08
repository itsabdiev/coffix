package kg.coffix.app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    HttpStatusCode httpStatusCode;
    String message;
    Timestamp timestamp;
}
