package org.awardis.pride.cloud;

import com.dropbox.core.DbxException;

import java.io.IOException;
import java.io.InputStream;

public interface CloudStorageClient {
    /**
     * Uploads file to the desired path.
     *
     * @param file file to upload.
     * @param path desired path.
     * @throws IOException
     * @throws DbxException
     */
    void  uploadFile(InputStream file, String path) throws IOException, DbxException;

    /**
     * Creates shared link for the object, which located by  desired path.
     *
     * @param path desired path.
     * @return Object shared link.
     * @throws DbxException
     */
    String createSharedLink(String path) throws DbxException;
}
