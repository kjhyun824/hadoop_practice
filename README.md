# Hadoop Practice Repository

## Environments
- OS - Ubuntu 18.04
### Installation
1. Install OpenJDK
```shell
sudo apt update
sudo apt install openjdk-8-jdk -y

// Check installation
java -version
javac -verison
```
2. Install OpenSSH
```shell
sudo apt install openssh-server openssh-client -y
```
3. Add user for hadoop as sudoer
```shell
sudo add user hduser
sudo chmod +x /etc/sudoers
sudo vim /etc/sudoers
// Add hduser as sudoers
sudo chmod -x /etc/sudoers
```
4. Generate ssh-key & save to authorization
```shell
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa/.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys

// Check passwordless connect to localhost
ssh localhost
```

**If you have cluster, send your generated key for all your machines**

5. Download Hadoop
```shell
// You can choose version whatever you want
wget https://downloads.apache.org/hadoop/common/hadoop-3.2.1/hadoop-3.2.1.tar.gz
tar xzf hadoop-3.2.1.tar.gz
```
### Basic Configuration
1. Configure Hadoop environments
```vim
// Append to ~/.bashrc
export HADOOP_HOME=/home/hdoop/hadoop-3.2.1
export HADOOP_INSTALL=$HADOOP_HOME
export HADOOP_MAPRED_HOME=$HADOOP_HOME
export HADOOP_COMMON_HOME=$HADOOP_HOME
export HADOOP_HDFS_HOME=$HADOOP_HOME
export YARN_HOME=$HADOOP_HOME
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin
export HADOOP_OPTS"-Djava.library.path=$HADOOP_HOME/lib/nativ"
```
Apply the configuration
```shell
source ~/.bashrc
```
2. Configure Java Path for Hadoop
```shell
// Find your Java Path
which javac
readlink -f /usr/bin/javac (Result of upper command)
```
```shell
sudo vim $HADOOP_HOME/etc/hadoop/hadoop-env.sh
```
Find `JAVA_HOME` and edit
```vim
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
```
3. Copy basic configure files
```shell
sudo cp conf/core-site.xml $HADOOP_HOME/etc/hadoop/core-site.xml
sudo cp conf/hdfs-site.xml $HADOOP_HOME/etc/hadoop/hdfs-site.xml
sudo cp conf/mapred-site.xml $HADOOP_HOME/etc/hadoop/mapred-site.xml
sudo cp conf/yarn-site.xml $HADOOP_HOME/etc/hadoop/yarn-site.xml
```
### Execution
```shell
./start-dfs.sh
./start-yarn.sh

# Check Hadoop running
jps
```
### Access Hadoop UI from Browser
- Namenode Link : `http://localhost:9870`
- Datanode Link : `http://localhost:9864`
- YARN Resource Manager Link : `http://localhost:8088`

**I'll find how to configure the ports later**

## MapReduce
### Password Cracker
- Decrypt md5 hashed password
- CPU Centric Practice
### WordCount
- The simplest MapReduce example with Hadoop
- Count the number of words in the text file
### study_practice
- Practice MapReduce on Book
