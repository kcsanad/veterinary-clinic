# Veterinary Clinic
This program simulates a busy veterinary clinic where your loved pet becomes healthy once again.

## Introduction
Code behind this Veterinary Clinic runs in K8S.
Backend code reads the provided YAML input file as Configmap (for demonstration purpose only).
There is a scheduled CronJOB which simulates the consumer part (in this case a BusyBox with Curl).

All is deployed by HELM, located in project structure, as well as necessary Dockerfiles definition.

## Deployment

1. kubectl create ns veterinary-clinic

1. helm upgrade --install clinic -n veterinary-clinic ./
   Default values.yaml is provided in charts directory

## Uninstall
helm delete clinic -n veterinary-clinic