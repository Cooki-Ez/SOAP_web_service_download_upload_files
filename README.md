# SOAP_web_service_download_upload_files
--------
TASK
--------
Based on samples provided for JAX-WS, create a SOAP web service enabling uplo-ading and downloading files.
Each file uploaded to your web service should be storedalong with its metadata:
•file name;
•its size;
•keywords describing its content.
The web service should support three types of requests:
•file upload — used for file uploading. Some of the aforementioned properties, such as the file name or file size, could be retrieved from the file itself.
Keywords should be provided as separate set of values in the request;
•file query — used for filtering files for given keywords;
•file download — for downloading a file based on its name.
Using the samples provided, create a JUnit test for verifying whether your SOAP web service works as expected.
