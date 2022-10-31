<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Users</h1>

        <table>
            <tr>
                <th>Email</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Role</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstname}</td>
                    <td>${user.lastname}</td>
                    <td>${user.role.rolename}</td>
                    <td><a href="<c:url value="user">
                               <c:param name="action" value="edit"/>
                               <c:param name="useremail" value="${user.email}"/>
                           </c:url>">Edit</a>
                    </td>
                    <td><a href="<c:url value="user">
                               <c:param name="action" value="delete"/>
                               <c:param name="useremail" value="${user.email}"/>
                           </c:url>">Delete</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
        <c:if test="${selectedUser eq null}">
            <h2>Add User</h2>
            <form action="users" method="post">
                Email: <input type="text" name="email" value="${user.email}"><br>
                First Name: <input type="text" name="firstname" value="${user.firstname}"><br>
                Last Name: <input type="text" name="firstname" value="${user.lastname}"><br>
                Password: <input type="password" name="password" value="${user.password}"><br>
                <lable for="role">Role: </lable>
                <select id="role" name="role">
                    <c:forEach items="${roles}" var="role">
                        ${role.rolename}
                    </c:forEach>    
                </select>
                <input type="hidden" name="action" value="insert"><br>
                <input type="submit" value="Add User"><br>
            </form>
        </c:if>

        <c:if test="${selectedUser eq 'edit'}">
            <h2>Edit User</h2>
            <form action="users" method="post">
                Email: ${user.email}><br>
                First Name: <input type="text" name="firstname" value="${user.firstname}"><br>
                Last Name: <input type="text" name="firstname" value="${user.lastname}"><br>
                Password: <input type="password" name="password" value="${user.password}"><br>
                <lable for="role">Role: </lable>
                <select id="role" name="role">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.roleId}"
                                <c:if test="${user.role.roleId == role.roleId}"> selected</c:if>
                                >${role.roleName}</option>
                    </c:forEach>    
                </select>
                <input type="hidden" name="action" value="update"><br>
                <input type="submit" value="Update"><br>
            </form>
            <form action="users" method="post">
                <input type="hidden" name="action" value="cancel">
                <input type="submit" value="Cancel">
            </form>
        </c:if>
    </body> 
</html>
