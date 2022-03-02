## Kubectl

```shell
# get all pods
kubectl get all -n YourNamespace

# show pod status(why deployed faield, .etc.)
kubectl describe pod/edi-converter-684894989c-n667s -n YourNamespace

# logs
kubectl logs -f pod/edi-converter-684894989c-n667s -n fpos-visibility

# Ensure you're on the local cluster.
kubectl config use-context docker-desktop
``` 

