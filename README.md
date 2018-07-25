CRUD API REST Spring boot 


Packages

  config
    SecurityConfig.java
      //Setando USER and ADMIN Profiles
  
  endpoint
    StudentEndPoint.java
      //CRUD
      
  error
    CustomErrorType.java
    ErrorDetails.java
    ResourceNotFoundDetails.java
    ResourceNotFoundException.java
    ValidationErrorDetails.java
      //Tratamento de errors
  
  handler
    RestExceptionHandler.java
      //metodo para retornar as classes ResourceNotFoundException e MethodArgumentNotValidException com o mesmo padrao.
  
  model
    Student.java
    User.java
      //Gets and Sets
  
  repository
    StudentRepository.java
    UserRepository.java
      //Queries
  
  service
    CustomUserDetailService.java
      //Validando grants do usuario que esta fazendo a requisicao POST, PUT ou DELETE
  
  util
    SpringBootEssentialsApplication
      //Metodo main
        
