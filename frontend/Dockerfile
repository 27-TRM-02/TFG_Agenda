FROM ubuntu:20.04
ARG username
ARG uid
ARG gid

# Ensure the user in the container is the same as the host
RUN (groupadd --gid $gid "$username" || groupadd "$username" || true) && (useradd  -l -m -s "/bin/bash" --gid "$username" --comment '' --uid $uid "$username" || useradd  -l -m -s "/bin/bash" --gid "$username" --comment '' "$username")
RUN apt update && apt install wget -y
RUN wget -O - https://deb.nodesource.com/setup_16.x -o nodesource_setup.sh | bash
RUN apt update && apt install nodejs -y
RUN rm ./nodesource_setup.sh
RUN mkdir /home/$username/app
COPY . /home/$username/app
RUN chown -R $username:$username /home/$username/app
WORKDIR /home/$username/app
RUN npm install -g @angular/cli
RUN npm install
# Uncomment below line to auto-start app on container build
# ENTRYPOINT ["ng serve"]