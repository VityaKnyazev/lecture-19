<h1>lecture-19</h1>

<p>Home task lecture 19:</p>
<ol>
<li>All home task rules (git hub, gitignore, maven, tests)</li>
<li>new project</li>
<li>new Pojo classes with inheritance (at least 1 parent)</li>
<li>Use each of 3 JPA inheritance strategy</li>
<li>DAO</li>
<li>Tests</li>
</ol>


<h2>What's done:</h2>
<ol>
<li>Created project to test different JPA strategies.</li>
<li>Created PPOJO's and DAO's for Mapped by superclass, single table strategies.</li>
<li>Added tests for DAO</li>
</ol>

<h3>To run App you should:</h3>
<ol>
<li>Build project: $mvn clean package</li>
<li>Run new mysql server for the App: $docker-compose up -d</li>
<li>Run liquibase to create tables and insert data: $mvn liquibase:update</li>
<li>Run App</li>
</ol>