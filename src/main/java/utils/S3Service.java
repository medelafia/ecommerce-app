package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import jakarta.servlet.http.Part;
import software.amazon.awssdk.arns.Arn;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Service {
	
	
	private String accessId  ; 
	private  String secret  ; 
	private String bucketName = System.getenv("S3_BUCKET") ;
 	private static  S3Client s3Client ; 
	
	public S3Service() { 
		accessId =  System.getenv("S3_ACCESS_KEY") ; 
		secret = System.getenv("S3_SECRET_KEY") ; 
		bucketName = System.getenv("S3_BUCKET_NAME") ;
	}
	
	public S3Client getClient() { 
		if(s3Client == null ) { 
			AwsCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider
					.create(AwsBasicCredentials.create(accessId, secret)) ; 
			return S3Client
			.builder()
			.region(Region.EU_NORTH_1)
			.credentialsProvider(awsCredentialsProvider)
			.httpClientBuilder(UrlConnectionHttpClient.builder()) 
			.build() ; 
		}
		return s3Client ; 
	}
	
	
	public String upload(Part part) throws S3Exception, AwsServiceException, SdkClientException, IOException {  
		String fileName =  UUID.randomUUID().toString() + part.getSubmittedFileName() ; 
		this.getClient()
			.putObject(PutObjectRequest.builder().contentType(part.getContentType()).bucket(this.bucketName).key(fileName).build()  , RequestBody.fromBytes(part.getInputStream().readAllBytes() ) )  ; 
		
		return fileName ; 
		
	}
	public ResponseInputStream<GetObjectResponse> getImage(String fileKey)  { 
		return this.getClient().getObject(t -> t.bucket(this.bucketName).key(fileKey)); 
	}
	
	
}
