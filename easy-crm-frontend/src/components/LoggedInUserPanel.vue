<script setup lang="ts">
import { useKeycloak } from '@dsb-norge/vue-keycloak-js'

const kc = useKeycloak()

const name = kc.fullName

const logout = () => kc.logoutFn?.()
</script>
<template>
  <div class="flex justify-center items-center gap-3">
    <div class="flex gap-2">
      <span>Eingeloggt als:</span>
      <span class="font-bold">{{ name }}</span>
    </div>
    <div class="h-5 border-l border-l-gray-600"></div>
    <button
      @click="logout"
      class="px-3 py-1 rounded-xl bg-slate-400 flex justify-center items-center gap-2"
    >
      Ausloggen
      <span class="material-symbols-outlined">logout</span>
    </button>
  </div>
</template>
