package com.docdoku.server.rest.file;

import org.junit.Test;

public class PartBinaryResourceTest {
    private static final String WORKSPACE_ID="TestWorkspace";
    private static final String PART_ID="TestPart";
    private static final String VERSION="A";
    private static final int ITERATION=1;
    private static final String SOURCE_FILE_STORAGE="test/file/toUpload";
    private static final String FILE_STORAGE="test/file/uploaded";
    private static final String SOURCE_FILENAME1="/upload_0000000_0000000000__0000_00000011.tmp";
    private static final String SOURCE_FILENAME2_1="/upload_0000000_0000000000__0000_00000012.tmp";
    private static final String SOURCE_FILENAME2_3="/upload_0000000_0000000000__0000_00000013.tmp";
    private static final String FILENAME1= "com/docdoku/server/rest/file/TestFile.txt";
    private static final String FILENAME2="TestFile_With_éàè.txt";
    private static final String FILENAME3_1="TestFile2.txt";
    private static final String FILENAME3_2="TestFile3.txt";

    /**
     * Test to upload a file to a part
     * @throws Exception
     */
    @Test
    public void testUploadPartFiles1() throws Exception {
        //User Case1
        //workspaceId = WORKSPACE_ID
        //partId = PART_ID
        //version = VERSION
        //iteration = ITERATION
        //formParts = [{fileName = FILENAME1,
        //              repository= SOURCE_FILE_STORAGE,
        //              tempFile= SOURCE_FILE_STORAGE+"/"+SOURCE_FILENAME1}]
        //binaryResource = {
        //                    fullName= WORKSPACE_ID+"/"+PART_ID+"/"+VERSION+"/"+ITERATION+"/"+FILENAME1
        //                    contentLenght=O
        //                    lastModified = new Date();
        //                  }

        // assert uploaded file exist in TARGET_FILE_STORAGE+"/"+FILENAME1 et length > 0
        // assert response.status.code = "201"
        // assert response.getLocation().toString() = "/api/files/"+WORKSPACE_ID+"/"+PART_ID+"/"+VERSION+"/"+ITERATION+"/"+FILENAME1
    }

    /**
     * Test to upload a file to a part with special characters
     * @throws Exception
     */
    @Test
    public void testUploadPartFiles2() throws Exception {
        //User Case2
        //workspaceId = WORKSPACE_ID
        //partId = PART_ID
        //version = VERSION
        //iteration = ITERATION
        //formParts = [{fileName = FILENAME1,
        //              repository= SOURCE_FILE_STORAGE,
        //              tempFile= SOURCE_FILE_STORAGE+"/"+SOURCE_FILENAME1}]
        //binaryResource = {
        //                    fullName= WORKSPACE_ID+"/"+PART_ID+"/"+VERSION+"/"+ITERATION+"/"+FILENAME2
        //                    contentLenght=O
        //                    lastModified = new Date();
        //                  }

        // assert uploaded file exist in TARGET_FILE_STORAGE+"/"+FILENAME2 et length > 0
        // assert response.status.code = "201"
        // assert response.getLocation().toString() = "/api/files/"+WORKSPACE_ID+"/"+PART_ID+"/"+VERSION+"/"+ITERATION+"/"+FILENAME2
    }

    /**
     * Test to upload several file to a part
     * @throws Exception
     */
    @Test
    public void testUploadPartFiles3() throws Exception {
        //User Case3
        //workspaceId = WORKSPACE_ID
        //partId = PART_ID
        //version = VERSION
        //iteration = ITERATION
        //formParts = [{
        //                  fileName = FILENAME3_1,
        //                  repository= SOURCE_FILE_STORAGE,
        //                  tempFile= SOURCE_FILE_STORAGE+"/"+SOURCE_FILENAME2_1
        //              },{
        //                  fileName = FILENAME3_2,
        //                  repository= SOURCE_FILE_STORAGE,
        //                  tempFile= SOURCE_FILE_STORAGE+"/"+SOURCE_FILENAME2_1
        //              }
        //            ]
        //getBinaryResource(pk,filename,length) = {
        //                    fullName= WORKSPACE_ID+"/"+PART_ID+"/"+VERSION+"/"+ITERATION+"/"+filename
        //                    contentLength=length
        //                    lastModified = new Date();
        //                  }

        // assert uploaded file exist in TARGET_FILE_STORAGE+"/"+FILENAME3_1  et length > 0
        // assert uploaded file exist in TARGET_FILE_STORAGE+"/"+FILENAME3_2  et length > 0
        // assert response.status.code = "200"
    }


    /**
     * Test to download a part file as a guest and the part is public
     * @throws Exception
     */
    @Test
    public void testDownloadPartFile1() throws Exception {

    }

    /**
     * Test to download a part file as a guest but the part is not public
     * @throws Exception
     */
    @Test
    public void testDownloadPartFile2() throws Exception {

    }

    /**
     * Test to download a part file as a regular user who has read access on it
     * @throws Exception
     */
    @Test
    public void testDownloadPartFile3() throws Exception {

    }

    /**
     * Test to download a part file as a regular user who has no read access on it
     * @throws Exception
     */
    @Test
    public void testDownloadPartFile4() throws Exception {

    }
}