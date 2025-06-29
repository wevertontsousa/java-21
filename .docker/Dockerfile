FROM amazoncorretto:21-alpine3.21

RUN apk update && apk upgrade \
  && apk add --no-cache bash curl git openssh-client starship \
  && rm -rf /var/cache/apk/* \
  && addgroup -g 1000 appuser \
  && adduser -D -G appuser -u 1000 -s /bin/bash appuser \
  && mkdir -p /app \
  && echo 'eval "$(starship init bash)"' >> /home/appuser/.bashrc

USER appuser

WORKDIR /app