# gcloud artifacts repositories create api-editor-spring --repository-format=docker --location=europe-west1 --description="API for editor using Spring" 
gcloud builds submit --region=europe-west1 --tag europe-west1-docker.pkg.dev/writeopia/api-editor-spring/api_editor_spring:0.0.1