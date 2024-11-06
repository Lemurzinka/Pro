<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Login Form</title>
  </head>
  <body>

  <form action="/response" method="POST">
      Name: <input type="text" name ="name"><br>
      LastName: <input type="text" name="lastname"><br>
      Age: <input type="text" name="age"><br>
      Your favourite season:
      <select name="season">
      <option value="Winter">Winter</option>
      <option value="Spring">Spring</option>
      <option value="Summer">Summer</option>
      <option value="Autumn">Autumn</option>
      </select><br>
      What gadget do you usually use:
      <select name="gadget">
          <option value="PC">PC</option>
          <option value="Smartphone">Smartphone</option>
          <option value="Other">Other</option>
      </select><br>
      <input type="submit">
  </form>
  </body>
</html>
