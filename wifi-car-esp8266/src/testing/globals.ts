export default function () {
  process.env.NODE_ENV = "test";
  process.env.TZ = "Europe/Paris";
  process.env.LANG = "fr_FR.UTF-8";
}
