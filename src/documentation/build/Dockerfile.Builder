# Stage that builds the application, a prerequisite for the running stage
FROM maven:3-openjdk-17-slim as build
RUN curl -sL https://deb.nodesource.com/setup_16.x | bash -
RUN apt-get update -qq && apt-get install -qq --no-install-recommends nodejs

# Stop running as root at this point
RUN useradd -m myuser
WORKDIR /usr/src/app/
RUN chown myuser:myuser /usr/src/app/
USER myuser
