# neo4j-demo
A simple command line demo app

Using Docker on MacOS

Pull Neo4J image: docker pull neo4j

Run the Docker image:

docker run \
    --publish=7474:7474 --publish=7687:7687 \
    --volume=$HOME/neo4j/data:/data \
    neo4j

Access database from browser http://localhost:7474

Change Username/Password to neo4j/secret

Run spring boot application
