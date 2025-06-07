import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import VueKeycloak from '@dsb-norge/vue-keycloak-js'

import App from './App.vue'
import router from './router'

const app = createApp(App)

console.log(import.meta.env.VITE_KEYCLOAK_BASE_URL)

app.use(VueKeycloak, {
  config: {
    url: import.meta.env.VITE_KEYCLOAK_BASE_URL,
    realm: import.meta.env.VITE_KEYCLOAK_REALM,
    clientId: import.meta.env.VITE_KEYCLOAK_CLIENT_ID,
  },
  init: {
    onLoad: 'login-required',
    checkLoginIframe: false,
  },
  onReady: () => app.mount('#app'),
})
app.use(createPinia())
app.use(router)

