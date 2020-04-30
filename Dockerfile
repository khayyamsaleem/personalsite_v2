FROM nginx:stable-alpine

ENV HUGO_VERSION 0.69.0

WORKDIR /tmp
RUN apk add --no-cache --virtual .deps curl tar git make musl-dev go && \
    curl -L https://github.com/spf13/hugo/releases/download/v${HUGO_VERSION}/hugo_${HUGO_VERSION}_Linux-64bit.tar.gz > \
    hugo_${HUGO_VERSION}_Linux-64bit.tar.gz && \
    tar -zxvf hugo_${HUGO_VERSION}_Linux-64bit.tar.gz && \
    mv ./hugo /bin/hugo && \
    rm -rf /tmp && \
    apk del .deps

ENV GOROOT /usr/lib/go
ENV GOPATH /go
ENV PATH /go/bin:$PATH

WORKDIR /usr/src
COPY ./nginx.conf /etc/nginx/nginx.conf
EXPOSE 80

ADD . /usr/src
RUN go version
RUN hugo --verbose
