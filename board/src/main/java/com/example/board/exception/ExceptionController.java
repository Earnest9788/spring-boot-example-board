// package com.example.board.exception;

// import javax.servlet.http.HttpServletRequest;

// import org.springframework.core.Ordered;
// import org.springframework.core.annotation.Order;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.client.HttpServerErrorException;

// @ControllerAdvice
// public class ExceptionController {
    
//     @ExceptionHandler(Exception.class)
//     @ResponseBody
//     public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        
//         ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());

//         return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);

//     }

//     private ErrorResponse makeErrorResponse(BindingResult bindingResult) {
//         String code = "";
//         String description = "";
//         String detail = "";

//         if (bindingResult.hasErrors()) {
//             detail = bindingResult.getFieldError().getDefaultMessage();

//             String bindResultCode = bindingResult.getFieldError().getCode();

//             switch (bindResultCode) {
//                 case "NotEmpty":
//                     code = ErrorCode.NOT_EMPTY.getCode();
//                     description = ErrorCode.NOT_EMPTY.getDescription();
//                     break;
//             }

//         }

//         return new ErrorResponse(code, description, detail);
//     }

// }