FROM openjdk:11-jdk
ARG username
ARG uid
ARG gid

# Ensure the user in the container is the same as the host
RUN (groupadd --gid $gid "$username" || groupadd "$username" || true) && (useradd  -l -m -s "/bin/bash" --gid "$username" --comment '' --uid $uid "$username" || useradd  -l -m -s "/bin/bash" --gid "$username" --comment '' "$username")
RUN apt update && apt install maven -y
RUN mkdir /home/$username/app
COPY . /home/$username/app
RUN chown -R $username:$username /home/$username/app
WORKDIR /home/$username/app
# Uncomment below line to auto-start app on container build
# ENTRYPOINT ["./mvnw", "spring-boot:run"]