# dayTrip2ski

## Docker Trickery

If you are on a ARM64 platform (like Mac M1) first configure docker for
[multi arch builds](https://docs.docker.com/desktop/multi-arch/).

We use a custom build container for CircleCI and for multi step docker builds.
You can build and pus it like this (multi arch).

```bash
docker buildx build --platform linux/amd64,linux/arm64 -f Dockerfile.Builder -t se2beta/vaadin-builder:tag-name --push .
```
