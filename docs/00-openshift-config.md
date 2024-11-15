# Deploying on OpenShift

## Prerequisites
* Running OpenShift 4.x Cluster
*  Login to the OpenShift Cluster from the terminal
```
oc login --token=sha256~xxxx --server=https://api.ocp4.example.com:6443
```

## Manual Deployment

**1. Install OpenShift Devspaces Operator**
```
oc apply -k  cluster-config/devspaces/operator/overlays/stable
```

**2. Install OpenShift Devspaces Instance**
```
oc apply -k   cluster-config/devspaces/instance/overlays/default
```
**3. Under routes click on the URL under openshift-devspaces namespace**
![20241109150929](https://i.imgur.com/CBMbYz6.png)
**4. Login to Devspaces**
![20241109151029](https://i.imgur.com/U46XdYE.png)
**5. Create a new workspace from the below URL**  
*You may also want to fork the repo if you want to make custom changes*  
* `https://github.com/tosin2013/quarkus-holidays-ai.git`
![20241109180307](https://i.imgur.com/YDG5g9j.png)
**You can now access the workspace**
![20241109180709](https://i.imgur.com/6KKhJL2.png)
