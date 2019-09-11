一个前后端分离的个人博客。<br/>
帅哥前端人士：JavaLake
<br/>
屌丝后端人士：脐橙
<br/>
屌丝安卓人士：哽咽
<br/>
<a href="http://blog.javalake.top/">前端网址</a>
<a href="https://github.com/DuGun/blog/blob/master/exploitBlogData/blog.sql">mysql脚本</a>
<br/>
<br/>
<h1>后台所用的技术</h1>
<ul>
<li>SSM框架</li>
<li>MySql数据库</li>
<li>Redis缓存数据库</li>
  <li>Shiro权限框架</li>
  <li>validation校验框架</li>
  <li>ToKen</li>
  <li>雪花算法生成ID</li>
  <li>FileUtils</li>
  <li>FilenameUtils</li>
</ul>
<br/>
<h1>遇到的问题</h1>
<ul>
  <li>单/多文件上传存在不定性：可以通过将Request强转成MultipartHttpServletRequest，然后通过getFiles方法进行获取</li>
  <li>使用validation校验框架时使用@RequestBody导致不是抛出BindException，而是MethodArgumentNotValidException，其次就是获取异常信息。</li>
  <li>上一个demo项目中在使用Redis进行缓存Shiro权限类还是角色类出现复杂对象无法正常反序列化：使用Gson进行自定义反序列化</li>
  <li>使用JDK8新日期API中解决并发可能出现的时间问题</li>
</ul>


<br/>
<br/>
<h1>数据库外健关联图</h1>

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/%E5%A4%96%E5%81%A5%E9%99%90%E5%88%B6%E5%9B%BE%20%E4%B8%8B%E5%8D%887.35.45.jpg)


<h1>数据库设计</h1>

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/WechatIMG6.png)

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1(%E4%BA%8C).png)

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1(%E4%B8%89).png)

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1(%E5%9B%9B).png)

