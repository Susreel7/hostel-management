<%-- 
    Document   : unverifiedAccount
    Created on : Jun 23, 2022, 10:28:51 PM
    Author     : lekha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="mx-5 my-5 justify-content-center">
    <h1>Your Email is not verified</h1>
    <c:choose>
        <c:when test="${sent}">
            <form method="post">
                <input class="btn btn-green" type="submit" value="Resend verification">
            </form>
            <p>An verification email had been sent, please check your email. This verification email will expire in 5 minutes</p>
        </c:when>
        <c:otherwise>
            <form method="post">
                <input class="btn btn-green" type="submit" value="Verify Your Email">
            </form>
        </c:otherwise>
    </c:choose>
</div>