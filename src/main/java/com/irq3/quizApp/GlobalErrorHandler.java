package com.irq3.quizApp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
class GlobalErrorHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//
//        Map<String, String> fieldErrors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            fieldErrors.put(fieldName, errorMessage);
//        });
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("status", HttpStatus.BAD_REQUEST.value());
//        response.put("errors", fieldErrors);
//
//        return ResponseEntity.badRequest().body(response);
//    }
//
//    @ExceptionHandler(AuthorizationDeniedException.class)
//    public ResponseEntity<String> handleAuthDenied(){
//        return ResponseEntity.badRequest().body("Access denied");
//    }
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleError(RuntimeException runtimeException){
//        return ResponseEntity.badRequest().body(runtimeException.toString());
//    }

    @RequestMapping(value = "/test-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public String errorPage(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        Object message = request.getAttribute("jakarta.servlet.error.message");

        model.addAttribute("status", status);
        model.addAttribute("message", message);

        return "custom-error";
    }
}
