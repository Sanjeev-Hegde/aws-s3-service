import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import java.io.IOException;

public class S3Application {

    private static final AWSCredentials credentials;
    private static String bucketName;

    static {
        //put your accesskey and secretkey here
        credentials = new BasicAWSCredentials(
                "AKIAJCSNDJJ4WJOTKPYj",
                "L/QweU9RiNEhaTdXM62rnNK6IpOqFGqgaJdBFbTQ"
        );
    }

    public static void main(String[] args) throws IOException {
        //set-up the client
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1) // N.virginia // https://docs.aws.amazon.com/general/latest/gr/rande.html
                .build();
        AWSS3Service awsService = new AWSS3Service(s3client);

       //  AWSS3Service awsService = new AWSS3Service();
        // setting up default client taking key from environment variable
        // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
        bucketName = "sanjeevbuckettest";


        //list all the buckets
        for(Bucket s : awsService.listBuckets() ) {
            System.out.println(s.getName());
        }

//        //uploading object
//        awsService.putObject(
//                bucketName,
//                "Document/hello.txt",
//                new File("/Users/user/Document/hello.txt")
//        );

        //listing objects
        ObjectListing objectListing = awsService.listObjects(bucketName);
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            System.out.println(os.getKey());
        }

        //downloading an object
//        S3Object s3object = awsService.getObject(bucketName, "Document/hello.txt");
//        S3ObjectInputStream inputStream = s3object.getObjectContent();
//       // FileUtils.copyInputStreamToFile(inputStream, new File("/Users/user/Desktop/hello.txt"));
//        Files.copy(inputStream,"/Users/user/Desktop/hello.txt", StandardCopyOption.REPLACE_EXISTING);

    }
}