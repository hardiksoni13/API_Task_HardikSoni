package com.vinted.api.automation;

/**
 * We will manage all the URL in this class.
 * And host endpoint will be picked by from the property file based on the env.
 */
public class URLManagement {
    public static String createUserURL = "public/v1/users";
    public static String retrieveUserUsingIdURL = "public/v1/users/%s";
    public static String deleteUserURL = "public/v1/users/%s";
    public static String updateUserURL = "public/v1/users/%s";
    public static String retrieveAllUsersURL = "public/v1/users";

    public static String createPostURL = "public/v1/users/%s/posts";
    public static String retrievePostByUsrId = "public/v1/users/%s/posts";
    public static String retrieveAllPost = "public/v1/posts";

    public static String createCommentURL = "public/v1/posts/%s/comments";
}