<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/6/29
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <a href="springmvc/testViewAndViewResolver">Test ViewAndViewResolver</a>
  <br/>

  <%--
    模拟修改操作
    1. 原始数据为:1, Tom, 123, tom@123.com, 12
    2. 密码不能被修改
    3. 表单回显, 模拟操作直接在表单填写对应的属性值
  --%>
  <form action="springmvc/testModelAttribute" method="post">
      <input type="hidden" name="id" value="1"/>
      username: <input type="text" name="username" value="Jack"/>
      <br/>
      email: <input type="text" name="email" value="jack@123.com"/>
      <br/>
      age: <input type="text" name="age" value="20"/>
      <br/>
      <input type="submit" value="Submit"/>
  </form>
  <br/>
  <a href="springmvc/testSessionAttributes">Test SessionAndAttributes</a>
  <br/>
  <a href="springmvc/testMap">Test Map</a>
  <br/>
  <a href="springmvc/testModelAndView">Test ModelAndView</a>
  <br/>
  <a href="springmvc/testServletAPI">Test ServletAPI</a>
  <br/>

  <form action="springmvc/testPojo" method="post">
      username: <input type="text" name="username"/>
      <br/>
      password: <input type="password" name="password"/>
      <br/>
      email: <input type="text" name="email"/>
      <br/>
      age: <input type="text" name="age"/>
      <br/>
      city: <input type="text" name="address.city"/>
      <br/>
      province: <input type="text" name="address.province"/>
      <br/>
      <input type="submit" value="submit"/>
  </form>
  <br/>

  <a href="springmvc/testCookieValue">Test CookieValue</a>
  <br/>
  <a href="springmvc/testRequestHeader">Test RequestHeader</a>
  <br/>
  <a href="springmvc/testRequestParam?username=a&age=11">Test RequestParam</a>

  <br/><br/>
  <form action="springmvc/testRest/1" method="post">
      <input type="hidden" name="_method" value="PUT"/>
      <input type="submit" value="TestRest PUT"/>
  </form>
  <br/>
  <form action="springmvc/testRest/1" method="post">
      <input type="hidden" name="_method" value="DELETE"/>
      <input type="submit" value="TestRest DELETE"/>
  </form>
  <br/>
  <form action="springmvc/testRest" method="post">
      <input type="submit" value="TestRest POST"/>
  </form>
  <br/>
  <a href="springmvc/testRest/1">Test Rest Get</a>
  <br/>

  <a href="springmvc/testPathVariable/3">Test PathVariable</a>
  <br/>
  <a href="springmvc/testAntPath/fhowij/abc">Test AntPath</a>
  <br/>
  <a href="springmvc/testParamsAndHeaders?username=a&age=10">test ParamsAndHeaders</a>
  <br/>
  <form action="springmvc/testMethod" method="POST">
      <input type="submit" value="submit">
  </form>
  <br/>
  <a href="springmvc/testRequestMapping">Test Method</a>
  <br/>
  <a href="springmvc/testRequestMapping">Test RequestMapping</a>
  <br/>
  <a href="helloworld">Hello World</a>
  </body>
</html>
