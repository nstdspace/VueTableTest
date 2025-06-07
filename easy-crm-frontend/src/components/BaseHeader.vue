<script setup lang="ts">
import LoggedInUserPanel from '@/components/LoggedInUserPanel.vue'

defineProps<{
  visibleClientCount: number
}>()
</script>
<template>
  <header class="flex h-16 w-full items-center gap-10 bg-slate-300 p-5">
    <h1 class="rounded-xl bg-slate-400 px-3 py-1 text-lg font-bold">Kundenverwaltung</h1>
    <span>Angezeigte Einträge: {{ visibleClientCount }}</span>
    <LoggedInUserPanel class="ml-auto" />
  </header>
</template>
