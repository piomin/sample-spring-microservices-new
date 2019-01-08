## Running Java Microservices on OpenShift using Source-2-Image  [![Twitter](https://img.shields.io/twitter/follow/piotr_minkowski.svg?style=social&logo=twitter&label=Follow%20Me)](https://twitter.com/piotr_minkowski)

Detailed description can be found here: [Running Java Microservices on OpenShift using Source-2-Image](https://piotrminkowski.wordpress.com/2019/01/08/running-java-microservices-on-openshift-using-source-2-image/) 

$ oc login -u system:admin
$ oc policy add-role-to-user cluster-reader system:serviceaccount:myproject:default

$ kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default
